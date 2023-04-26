import React, {useContext, useEffect, useState} from "react";
import {Formik, Form, Field, ErrorMessage} from "formik";
import "./Login.css";
import NavigationCard from "components/navigation-card/NavigationCard";
import {loginUser} from "api/User";
import {UserContext} from "context/UserContext";
import * as Yup from 'yup';
import { useNavigate } from "react-router-dom";

function Login() {
  const {user, setUser} = useContext(UserContext);
  const [errorMessage, setErrorMesage] = useState();
  const [remindEmail, setRemindEmail] = useState("");
  const [remember, setRemember] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("email")){
      setRemindEmail(localStorage.getItem("email"));
      setRemember(true);
    }
  }, [])

  const handleLoginSubmit = async (args) => {
    try {
      const loggedUser = await loginUser(args);
      setUser(loggedUser.user);
      localStorage.setItem('token', loggedUser.token);
      //localStorage.setItem('user', JSON.stringify(loggedUser.user));
      if (args.remindMe)
        localStorage.setItem("email", args.email);
      else
        localStorage.removeItem("email");
      navigate("/");
    } catch (exception) {
      setErrorMesage(exception.response.data);
    }
  };

  const LoginSchema = Yup.object().shape({
    email: Yup.string().email('Invalid email').required('Required'),
    password: Yup.string().min(8, "Password should be 8 char minimum").required('Required')
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
            {({isSubmitting}) => (
              <Form className="login-form">
                <label className="formik-field-label" htmlFor="email">
                  Enter Email
                </label>
                <ErrorMessage name="email" component = "div" className="formik-error-message" />
                <Field
                  type="email"
                  name="email"
                  className="custom-formik-field"
                />
                <label className="formik-field-label" htmlFor="password">
                  Password
                </label>
                <ErrorMessage name="password" component="div" className="formik-error-message"/>
                <Field
                  type="password"
                  name="password"
                  className="custom-formik-field"
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
                <button className="submit-form-button" type="submit">
                  LOGIN
                </button>
              </Form>
            )}
          </Formik>
        </div>
      </div>
    </>
  );
}

export default Login;
