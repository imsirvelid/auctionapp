import { UserContext } from "context/UserContext";
import React, { useContext } from "react";
import { Navigate } from "react-router-dom";

function GuestRoute(props) {
  const {user} = useContext(UserContext);
  if (user)
    return <Navigate to="/" replace />
  return props.children;
}

export default GuestRoute;
