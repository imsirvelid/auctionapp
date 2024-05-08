import React, {useState, useMemo} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Footer from "components/footer/Footer";
import Navbar from "components/navbar/Navbar";
import About from "pages/about/About";
import TermsAndConditions from "pages/terms-and-conditions/TermsAndConditions";
import PrivacyPolicy from "pages/privacy-policy/PrivacyPolicy";
import Landing from "pages/landing/Landing";
import ProductOverview from "pages/product-overview/ProductOverview";
import Search from "pages/search/Search";
import "./App.css";
import Login from "pages/login/Login";
import {UserContext} from "context/UserContext";
import Register from "pages/register/Register";
import "./api/AxiosInterceptor";
import GuestRoute from "components/protected/GuestRoute";
import UserProfile from "pages/user-profile/UserProfile";
import "./api/AxiosInterceptor";
import PrivateRoute from "components/protected/PrivateRoute";
import {Stomp} from "@stomp/stompjs";
import SockJS from "sockjs-client";
import {NotificationContainer, NotificationManager} from "react-notifications";
import "../node_modules/react-notifications/lib/notifications.css";
import Notifications from "pages/notifications/Notifications";
import Sell from "pages/sell/Sell";
import PaySuccess from "pages/pay-success/PaySuccess";
import { StompSessionProvider, useSubscription} from "react-stomp-hooks";

function App() {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
  const value = useMemo(() => ({user, setUser}), [user, setUser]);

  
  const sockJS = new SockJS("http://localhost:8080/ws-message");

  const stompClient = Stomp.over(() => sockJS);
  

  const createNotification = (message, type) => {
    console.log("Napravi notifikciaju: ", type);
    return () => {
      switch (type) {
        case "INFO":
          NotificationManager.info(message);
          break;
        case "SUCCESS":
          NotificationManager.success(message, "You won");
          break;
        case "warning":
          NotificationManager.warning(
            "Warning message",
            "Close after 3000ms",
            3000
          );
          break;
        case "error":
          NotificationManager.error("Error message", "Click me!", 5000, () => {
            alert("callback");
          });
          break;
      }
    };
  };

  stompClient.connect({}, () => {
    stompClient.subscribe("/user/" + user.email + "/queue", (message) => {
      const payload = JSON.parse(message.body);
      console.log("Received message:", payload);
      createNotification(payload.message, "INFO")();
    });
    console.log("Here again");
  });

  return (
    <>
      <NotificationContainer />
      <Router>
        <UserContext.Provider value={value}>
          <Navbar />
          <div className="App">
            <Routes>
              <Route path="/" exact element={<Landing />} />
              <Route path="/" exact element={<h1>Test</h1>} />
              <Route path="/about" element={<About />} />
              <Route
                path="/terms-and-conditions"
                element={<TermsAndConditions />}
              />
              <Route path="/privacy-policy" element={<PrivacyPolicy />} />
              <Route path="/products/:id" element={<ProductOverview />} />
              <Route
                path="/search"
                element={<Search key={window.location.pathname} />}
              />
              <Route
                path="/user/login"
                element={
                  <GuestRoute>
                    <Login />
                  </GuestRoute>
                }
              />
              <Route
                path="/user/register"
                element={
                  <GuestRoute>
                    <Register />
                  </GuestRoute>
                }
              />
              <Route
                path="/user/profile"
                element={
                  <PrivateRoute>
                    <UserProfile />
                  </PrivateRoute>
                }
              />
              <Route
              path="/user/sell"
              element={
                <PrivateRoute>
                  <Sell />
                </PrivateRoute>
              }
            />
            <Route path="/payment/success/:id" element={<PaySuccess />} />
              <Route
                path="/user/notifications"
                element={
                  <PrivateRoute>
                    <Notifications />
                  </PrivateRoute>
                }
              />
            </Routes>
          </div>
          <Footer />
        </UserContext.Provider>
      </Router>
    </>
  );
}

export default App;
