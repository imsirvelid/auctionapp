import { UserContext } from "context/UserContext";
import React, { useContext } from "react";
import { Navigate } from "react-router-dom";

function Protected(props) {
  const {user} = useContext(UserContext);
  if (!user)
    return <Navigate to="/user/login" replace />
  return props.children;
}

export default Protected;
