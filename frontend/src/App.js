import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Footer from "components/footer/Footer";
import Navbar from "components/navbar/Navbar";
import About from "pages/about/About";
import TermsAndConditions from "pages/terms-and-conditions/TermsAndConditions";
import PrivacyPolicy from "pages/privacy-policy/PrivacyPolicy";
import Landing from "pages/landing/Landing";
import ProductOverview from "pages/product-overview/ProductOverview";
import Search from "pages/search/Search";

function App() {
  return (
    <Router>
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
          <Route path="/search" element={ <Search />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
