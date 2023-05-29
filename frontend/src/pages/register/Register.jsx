import React, {useContext, useState} from "react";
import * as Yup from "yup";
import {Formik, Form, Field, ErrorMessage} from "formik";
import "./Register.css";
import NavigationCard from "components/navigation-card/NavigationCard";
import {registerUser} from "api/User";
import {UserContext} from "context/UserContext";
import {Link, useNavigate} from "react-router-dom";

function Register() {
  const {setUser} = useContext(UserContext);
  const [errorMessage, setErrorMessage] = useState();
  const navigate = useNavigate();

  const handleRegisterSubmit = async (args) => {
    try {
      const loggedUser = await registerUser(args);
      setUser(loggedUser.user);
      localStorage.setItem("token", loggedUser.token);
      localStorage.setItem("user", JSON.stringify(loggedUser.user));
      navigate("/");
    } catch (exception) {
      setErrorMessage(exception.response.data);
    }
  };

  const RegisterSchema = Yup.object().shape({
    name: Yup.string()
      .min(2, "Name should contain at least 2 characters")
      .max(50, "Name must contain less than 50 characters")
      .required("Required"),
    surname: Yup.string()
      .min(2, "Last name should contain at least 2 characters")
      .max(50, "Last name must contain less than 50 characters")
      .required("Required"),
    email: Yup.string().email("Invalid email").required("Required"),
    password: Yup.string()
      .min(8, "Password should be 8 char minimum")
      .required("Required")
      .matches(
        /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.* ).{8,16}$/,
        "Password must contain at least one capitalized one small letter and special character"
      ),
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
            {() => (
              <Form className="login-form">
                <label className="formik-field-label" htmlFor="name">
                  First Name
                </label>

                <Field
                  type="text"
                  name="name"
                  className="custom-formik-field"
                />
                <ErrorMessage
                  name="name"
                  component="div"
                  className="formik-error-message"
                />
                <label className="formik-field-label" htmlFor="surname">
                  Last Name
                </label>

                <Field
                  type="text"
                  name="surname"
                  className="custom-formik-field"
                />
                <ErrorMessage
                  name="surname"
                  component="div"
                  className="formik-error-message"
                />
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
                <button className="submit-register-form-button" type="submit">
                  REGISTER
                </button>
              </Form>
            )}
          </Formik>
          <p className="centered-text">
            Already have an account?
            <Link className="purple-link" to="/user/login">
              Login
            </Link>
          </p>
        </div>
      </div>
    </>
  );
}

export default Register;
