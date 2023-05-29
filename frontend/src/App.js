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
import UserProfile from "pages/user-profile/UserProfile";
import GuestRoute from "components/protected/GuestRoute";
import "./api/AxiosInterceptor";
import PrivateRoute from "components/protected/PrivateRoute";
import Sell from "pages/sell/Sell";
import PaySuccess from "pages/pay-success/PaySuccess";

function App() {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
  const value = useMemo(() => ({user, setUser}), [user, setUser]);

  return (
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
            <Route path="/payment/success/:id" element={<PaySuccess />}></Route>
          </Routes>
        </div>
        <Footer />
      </UserContext.Provider>
    </Router>
  );
}

export default App;
