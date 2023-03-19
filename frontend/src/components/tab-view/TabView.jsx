import React, {useState} from "react";
import "./TabView.css";


function TabView(props) {
  const tabs = props.tabs;
  const [currentTab, setCurrentTab] = useState(tabs[0].id);

  return (
    <>
      <div className="tab-view-header">
        {true ? (
          tabs.map((tab) => (
            <p
              className={`tab-view-option ${
                tab.id === currentTab ? "tvo-selected" : ""
              }`}
              key={tab.id}
              onClick={() => setCurrentTab(tab.id)}
            >
              {tab.title}
            </p>
          ))
        ) : (
          <></>
        )}
      </div>
      {tabs.map((tab) => (
        tab.id === currentTab && tab.component
      ))}
    </>
  );
}

export default TabView;
