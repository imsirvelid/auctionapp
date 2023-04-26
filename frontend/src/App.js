import React, {useState, useMemo, useEffect} from "react";
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
import {getCurrentUser} from "api/User";
import Register from "pages/register/Register";
import UserProfile from "pages/user-profile/UserProfile";
import Protected from "components/protected/Protected";

function App() {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
  const value = useMemo(() => ({user, setUser}), [user, setUser]);

  useEffect(() => {
    getCurrentUser(localStorage.getItem("token"))
      .then((response) => {
        setUser(response.data);
        localStorage.setItem("user", JSON.stringify(response.data));
      })
      .catch((error) => {
        setUser(null);
      });
  }, []);

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
            <Route path="/user/login" element={<Login />} />
            <Route path="/user/register" element={<Register />} />
            <Route
              path="/user/profile"
              element={
                <Protected>
                  <UserProfile />
                </Protected>
              }
            />
          </Routes>
        </div>
        <Footer />
      </UserContext.Provider>
    </Router>
  );
}

export default App;
