import React, {useState} from "react";
import "./UserProfile.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faGavel, faList, faPlus} from "@fortawesome/free-solid-svg-icons";
import NavigationCard from "components/navigation-card/NavigationCard";
import ProfileBidsTab from "components/profile-bids-tab/ProfileBidsTab";
import ProfileSellerTab from "components/profile-seller-tab/ProfileSellerTab";
import { Link } from "react-router-dom";

function UserProfile() {
  const [currentTab, setCurrentTab] = useState(0);
  const tabs = [
    {
      name: "Seller",
      component: <ProfileSellerTab/>,
      icon: faList,
    },
    {
      name: "Bids",
      component: <ProfileBidsTab/>,
      icon: faGavel,
    },
  ];
  return (
    <>
      <NavigationCard
        name={tabs[currentTab].name}
        link="My Account"
        subLink={tabs[currentTab].name}
        linkTo="/user/profile"
      />
      <div className="container">
        {tabs.map((tab, index) => (
          <button
            className={`profile-tab-button ${
              index === currentTab && "selected-profile-button"
            }`}
            key={index}
            onClick={(e) => setCurrentTab(index)}
          >
            <FontAwesomeIcon icon={tab.icon} /> &nbsp;
            {tab.name}
          </button>
        ))}
        <button className="profile-tab-button right"><Link to={"/user/sell"}><FontAwesomeIcon icon={faPlus} /> ADD ITEM</Link></button>
        <div className="profile-tab-component"></div>
        {tabs[currentTab].component}
      </div>
    </>
  );
}

export default UserProfile;
