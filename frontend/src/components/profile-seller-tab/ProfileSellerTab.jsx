import React, {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faGavel, faList} from "@fortawesome/free-solid-svg-icons";
import NavigationCard from "components/navigation-card/NavigationCard";
import ProfileBidsTab from "components/profile-bids-tab/ProfileBidsTab";

function ProfileSellerTab() {
  const [currentTab, setCurrentTab] = useState(0);
  const tabs = [
    {
      name: "Seller",
      component: <div>Seller tab</div>,
      icon: faList,
    },
    {
      name: "Bids",
      component: <ProfileBidsTab />,
      icon: faGavel,
    },
  ];
  return (
    <>
      <div className="container.">
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
        <div className="profile-tab-component"></div>
        {tabs[currentTab].component}
      </div>
    </>
  );
}

export default ProfileSellerTab;
