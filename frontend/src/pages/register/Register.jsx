import React, {useContext, useState} from "react";
import * as Yup from 'yup';
import {Formik, Form, Field, ErrorMessage} from "formik";
import "./Register.css";
import NavigationCard from "components/navigation-card/NavigationCard";
import {loginUser, registerUser} from "api/User";
import {UserContext} from "context/UserContext";
import { Link, useNavigate } from "react-router-dom";

function Register() {
  const {user, setUser} = useContext(UserContext);
  const [errorMessage, setErrorMesage] = useState();
  const navigate = useNavigate();

  const handleRegisterSubmit = async (args) => {
    try {
      const loggedUser = await registerUser(args);
      setUser(loggedUser.user);
      localStorage.setItem("token", loggedUser.token);
      localStorage.setItem("user", JSON.stringify(loggedUser.user));
      navigate("/");
    } catch (exception) {
      setErrorMesage(exception.response.data);
    }
  };

  const RegisterSchema = Yup.object().shape({
    name: Yup.string()
      .min(2, 'Too Short!')
      .max(50, 'Too Long!')
      .required('Required'),
    surname: Yup.string()
      .min(2, 'Too Short!')
      .max(50, 'Too Long!')
      .required('Required'),
    email: Yup.string().email('Invalid email').required('Required'),
    password: Yup.string().min(8, "Password should be 8 char minimum").required('Required')
  });

  return (
    <>
      <NavigationCard name="Login" link="" subLink="" linkTo="" />
      <div className="container-55">
        <div className="form-container">
          <h2 className="form-title"> REGISTER </h2>
          {errorMessage && (
            <p className="login-error-message">{errorMessage}</p>
          )}
          <Formik
            initialValues={{
              name: "",
              surname: "",
              email: "",
              password: "",
            }}
            onSubmit={(values) => handleRegisterSubmit(values)}
            validationSchema={RegisterSchema}
          >
            {({isSubmitting}) => (
              <Form className="login-form">
                <label className="formik-field-label" htmlFor="name">
                  First Name
                </label>
                <ErrorMessage name="name" component="div" className="formik-error-message"/>
                <Field
                  type="text"
                  name="name"
                  className="custom-formik-field"
                />
                <label className="formik-field-label" htmlFor="surname">
                  Last Name
                </label>
                <ErrorMessage name="surname" component="div" className="formik-error-message"/>
                <Field
                  type="text"
                  name="surname"
                  className="custom-formik-field"
                />
                <label className="formik-field-label" htmlFor="email">
                  Enter Email
                </label>
                <ErrorMessage name="email" component="div" className="formik-error-message"/>
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
                <button className="submit-form-button" type="submit">
                  REGISTER
                </button>
              </Form>
            )}
          </Formik>
          <p className="centered-text">Already have an acount? <Link className="purple-link" to="/user/login">Login</Link></p>
        </div>
      </div>
    </>
  );
}

export default Register;
