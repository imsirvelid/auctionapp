import React, {useEffect, useState} from "react";
import {Formik, Form, Field, ErrorMessage} from "formik";
import * as Yup from "yup";
import "./LocationAndShipping.css";
import Button from "components/button/Button";
import {getUserCreditCardInfo} from "api/CreditCard";
import {getAllCitiesForCountry, getAllCountries} from "api/Country";

function LocationAndShipping(props) {
  const [allCountries, setAllCountries] = useState([]);
  const [allCities, setAllCities] = useState([]);

  const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const handleLoginSubmit = (values) => {
    props.onDone();
  };
  const LoginSchema = Yup.object().shape({
    address: Yup.string().required("Required"),
    country: Yup.string().required("Required"),
    phoneNumber: Yup.number().required("Required"),
    nameOnCard: Yup.string().required("Required"),
    cardNumber: Yup.string().required("Required"),
    expMonth: Yup.string().required("Required"),
    expYear: Yup.string().required("Required"),
    zipCode: Yup.string().required("Required"),
    city: Yup.string().required("Required"),
    cvc: Yup.string().required("Requried"),
  });

  useEffect(() => {
    window.scrollTo(0, 0);
    getAllCountries().then((response) => {
      setAllCountries(response);
    });
  }, []);

  useEffect(() => {
    getUserCreditCardInfo()
      .then((response) => {
        if (props.creditCardInfo.value) return true;
        props.creditCardInfo.set(true);
        if (
          !props.cardNumber.value &&
          !props.nameOnCard.value &&
          !props.expMonth.value &&
          !props.expYear.value &&
          !props.cvc.value
        ) {
          props.cardNumber.set(response.cardNumber);
          props.nameOnCard.set(response.nameOnCard);
          props.expMonth.set(response.expirationMonth - 1);
          props.expYear.set(response.expirationYear);
          props.address.set(response.user.address);
          props.country.set(response.user.country);
          props.phoneNumber.set(response.user.phone);
          props.cvc.set(response.cvc);
          props.creditCardId.set(response.id);
          props.zipCode.set(response.user.zipCode);
          props.city.set(response.user.city);
        }
      })
      .catch(() => {});
  });

  useEffect(() => {
    if (props.country.value)
      getAllCitiesForCountry(props.country.value).then((response) => {
        setAllCities(response);
      });
  }, [props.country.value]);

  return (
    <div className="container-55">
      <div className="form-container">
        <h2 className="form-title"> LOCATION & SHIPPING </h2>
        <Formik
          enableReinitialize
          initialValues={{
            address: props.address.value,
            country: props.country.value,
            phoneNumber: props.phoneNumber.value,
            nameOnCard: props.nameOnCard.value,
            cardNumber: props.cardNumber.value,
            expMonth: props.expMonth.value,
            expYear: props.expYear.value,
            city: props.city.value,
            zipCode: props.zipCode.value,
            cvc: props.cvc.value,
          }}
          validationSchema={LoginSchema}
          onSubmit={(values) => handleLoginSubmit(values)}
        >
          {() => (
            <Form className="add-item-form">
              <label className="formik-field-label" htmlFor="email">
                Address
              </label>
              <Field
                type="text"
                name="address"
                value={props.address.value}
                onChange={(e) => props.address.set(e.target.value)}
                placeholder="eg. Zmaja od Bosne bb"
                className="custom-formik-field"
              />
              <ErrorMessage
                name="address"
                component="div"
                className="formik-error-message"
              />
              <div className="country-city-div">
                <div className="country-div">
                  <label
                    className="formik-field-label full-width date-label"
                    htmlFor="email"
                  >
                    Country
                  </label>
                  <Field
                    value={props.country.value}
                    onChange={(e) => props.country.set(e.target.value)}
                    as="select"
                    name="country"
                    placeholder="eg. Spain"
                    className="custom-formik-field full-width"
                  >
                    {allCountries.map((country, index) => (
                      <option key={index} value={country.code}>
                        {country.name}
                      </option>
                    ))}
                  </Field>
                  <ErrorMessage
                    name="country"
                    component="div"
                    className="formik-error-message"
                  />
                </div>
                <div className="country-div">
                  <label
                    className="formik-field-label full-width date-label"
                    htmlFor="email"
                  >
                    City
                  </label>
                  <Field
                    value={props.city.value}
                    onChange={(e) => props.city.set(e.target.value)}
                    as="select"
                    name="country"
                    placeholder="eg. Madrid"
                    className="custom-formik-field full-width "
                  >
                    {allCities.map((city, index) => (
                      <option key={index} value={city}>
                        {city}
                      </option>
                    ))}
                  </Field>
                  <ErrorMessage
                    name="city"
                    component="div"
                    className="formik-error-message"
                  />
                </div>
              </div>
              <label className="formik-field-label" htmlFor="email">
                Zip Code
              </label>
              <Field
                value={props.zipCode.value}
                onChange={(e) => props.zipCode.set(e.target.value)}
                type="text"
                name="zipCode"
                placeholder="eg. 70230"
                className="custom-formik-field"
              />
              <ErrorMessage
                name="zipCode"
                component="div"
                className="formik-error-message"
              />
              <label className="formik-field-label" htmlFor="email">
                Phone Number
              </label>
              <Field
                value={props.phoneNumber.value}
                onChange={(e) => props.phoneNumber.set(e.target.value)}
                type="text"
                name="phoneNumber"
                placeholder="eg. 061 234 567"
                className="custom-formik-field"
              />
              <ErrorMessage
                name="phoneNumber"
                component="div"
                className="formik-error-message"
              />
              <p className="feature-products-text">Feature Products</p>
              <div className="gray-line"></div>
              <p className="accepted-cards">
                We accept the following credit cards{" "}
                <i className="fa-brands fa-cc-amex cc-icon" />
                <i className="fa-brands fa-cc-mastercard cc-icon" />
                <i className="fa-brands fa-cc-visa cc-icon" />
              </p>
              <label className="formik-field-label" htmlFor="email">
                Name on Card
              </label>
              <Field
                value={props.nameOnCard.value}
                onChange={(e) => props.nameOnCard.set(e.target.value)}
                type="text"
                name="nameOnCard"
                placeholder="eg. John Doe"
                className="custom-formik-field"
              />
              <ErrorMessage
                name="nameOnCard"
                component="div"
                className="formik-error-message"
              />
              <label className="formik-field-label" htmlFor="email">
                Card Number
              </label>
              <Field
                value={props.cardNumber.value}
                onChange={(e) => props.cardNumber.set(e.target.value)}
                type="text"
                name="cardNumber"
                placeholder="XXXX-XXXX-XXXX-XXXX"
                className="custom-formik-field"
              />
              <ErrorMessage
                name="cardNumber"
                component="div"
                className="formik-error-message"
              />
              <div className="one-row">
                <div className="expiration-date">
                  <label
                    className="formik-field-label total-width"
                    htmlFor="email"
                  >
                    Expiration Date
                  </label>
                  <div className="expiration-date-dropdowns">
                    <Field
                      value={props.expMonth.value}
                      onChange={(e) => props.expMonth.set(e.target.value)}
                      as="select"
                      name="expMonth"
                      className="custom-formik-field expiration-dropdown"
                    >
                      {months.map((month, index) => (
                        <option key={index} value={index}>
                          {month}
                        </option>
                      ))}
                    </Field>
                    <ErrorMessage
                      name="expMonth"
                      component="div"
                      className="formik-error-message"
                    />
                    <Field
                      value={props.expYear.value}
                      onChange={(e) => props.expYear.set(e.target.value)}
                      as="select"
                      name="expYear"
                      className="custom-formik-field expiration-dropdown"
                    >
                      <option value="red">YY</option>
                      <option value="2023">2023</option>
                      <option value="2024">2024</option>
                      <option value="2025">2025</option>
                      <option value="2026">2026</option>
                      <option value="2027">2027</option>
                      <option value="2028">2028</option>
                    </Field>
                    <ErrorMessage
                      name="expYear"
                      component="div"
                      className="formik-error-message"
                    />
                  </div>
                </div>
                <div className="cvc-cvv">
                  <label
                    className="formik-field-label total-width no-margin"
                    htmlFor="email"
                  >
                    CVC/CVV
                  </label>
                  <Field
                    value={props.cvc.value}
                    onChange={(e) => props.cvc.set(e.target.value)}
                    type="password"
                    name="cvc"
                    placeholder="***"
                    className="custom-formik-field cvc-width"
                  />
                  <ErrorMessage
                    name="cvc"
                    component="div"
                    className="formik-error-message"
                  />
                </div>
              </div>

              <div className="form-bottom-buttons">
                <Button type="white">CANCEL</Button>
                <div className="right-form-buttons">
                  <Button type="white" onClick={props.onBack}>
                    BACK
                  </Button>
                  <button type="submit" className="button purple">
                    DONE
                  </button>
                </div>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default React.memo(LocationAndShipping);
