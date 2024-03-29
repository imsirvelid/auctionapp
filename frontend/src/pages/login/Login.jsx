import React, {useContext, useEffect, useState} from "react";
import {Formik, Form, Field, ErrorMessage} from "formik";
import "./Login.css";
import NavigationCard from "components/navigation-card/NavigationCard";
import {loginUser} from "api/User";
import {UserContext} from "context/UserContext";
import * as Yup from "yup";
import {useNavigate} from "react-router-dom";
import { setupFirebaseConfig } from "config/firebase";
import Button from "components/button/Button";

function Login() {
  const {setUser} = useContext(UserContext);
  const [errorMessage, setErrorMessage] = useState();
  const [remindEmail, setRemindEmail] = useState("");
  const [remember, setRemember] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("email")) {
      setRemindEmail(localStorage.getItem("email"));
      setRemember(true);
    }
  }, []);

  const handleLoginSubmit = async (args) => {
    try {
      const loggedUser = await loginUser(args);
      setUser(loggedUser.user);
      setupFirebaseConfig();
      localStorage.setItem("token", loggedUser.token);
      localStorage.setItem("user", JSON.stringify(loggedUser.user));
      if (args.remindMe) localStorage.setItem("email", args.email);
      else localStorage.removeItem("email");
      navigate("/");
    } catch (exception) {
      setErrorMessage(exception.response.data);
    }
  };

  const LoginSchema = Yup.object().shape({
    email: Yup.string().required("Required"),
    password: Yup.string()
      .required("Required"),
  });

  return (
    <>
      <NavigationCard name="Login" link="" subLink="" linkTo="" />
      <div className="container-55">
        <div className="form-container">
          <h2 className="form-title"> LOGIN </h2>
          {errorMessage && (
            <p className="login-error-message">{errorMessage}</p>
          )}
          <Formik
            enableReinitialize
            initialValues={{
              email: remindEmail,
              password: "",
              remindMe: remember,
            }}
            validationSchema={LoginSchema}
            onSubmit={(values) => handleLoginSubmit(values)}
          >
            {() => (
              <Form className="login-form">
                <label className="formik-field-label" htmlFor="email">
                  Enter Email
                </label>
                <Field
                  type="email"
                  name="email"
                  className="custom-formik-field"
                />
                <ErrorMessage
                  name="email"
                  component="div"
                  className="formik-error-message"
                />
                <label className="formik-field-label" htmlFor="password">
                  Password
                </label>
                <Field
                  type="password"
                  name="password"
                  className="custom-formik-field"
                />
                <ErrorMessage
                  name="password"
                  component="div"
                  className="formik-error-message"
                />
                <div className="checkbox-container">
                  <Field
                    className="check-field-input"
                    type="checkbox"
                    name="remindMe"
                  />
                  <label className="checkbox-field-label" htmlFor="remindMe">
                    Remember me
                  </label>
                </div>
                <Button className="submit-login-form-button" type="white purple-border" buttonType="submit">
                  LOGIN
                </Button>
              </Form>
            )}
          </Formik>
        </div>
      </div>
    </>
  );
}

export default Login;
