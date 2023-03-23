import React, {useState} from "react";
import "./TabView.css";


function TabView(props) {
  const tabs = props.tabs;
  const [currentTab, setCurrentTab] = useState(0);

  return (
    <>
      <div className="tab-view-header">
        {true ? (
          tabs.map((tab, index) => (
            <p
              className={`tab-view-option ${
                index === currentTab ? "tvo-selected" : ""
              }`}
              key={tab.id}
              onClick={() => setCurrentTab(index)}
            >
              {tab.title}
            </p>
          ))
        ) : (
          <></>
        )}
      </div>
      {tabs[currentTab].component}
    </>
  );
}

export default TabView;
