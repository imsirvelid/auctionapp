import React, {useEffect} from "react";
import {Formik, Form, Field, ErrorMessage} from "formik";
import * as Yup from "yup";
import "./SetPrices.css";
import Button from "components/button/Button";

function SetPrices(props) {

  const digitsOnly = (value) => /^\d+(\.\d+)?$/.test(value);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const validationSchema = Yup.object().shape({
    startingPrice: Yup.string()
      .required("Required")
      .test("Digits only", "Digits only", digitsOnly)
      .min(1, "At least one digit")
      .max(15, "Maximum number limits"),
    startDate: Yup.date().required("Required"),
    endDate: Yup.date().required("Required"),
  });

  const setPricesSubmit = () => {
    props.onNext();
  };

  return (
    <div className="container-55">
      <div className="form-container">
        <h2 className="form-title"> SET PRICES </h2>
        <Formik
          enableReinitialize
          initialValues={{
            startingPrice: props.startingPrice.value,
            startDate: props.startDate.value,
            endDate: props.endDate.value,
          }}
          validationSchema={validationSchema}
          onSubmit={(values) => setPricesSubmit(values)}
        >
          {() => (
            <Form className="add-item-form">
              <label className="formik-field-label" htmlFor="email">
                Your start Price
              </label>
              <Field
                type="text"
                name="startingPrice"
                value={props.startingPrice.value}
                onChange={(e) => props.startingPrice.set(e.target.value)}
                placeholder="eg. 1500"
                className="custom-formik-field"
              />
              <ErrorMessage
                name="startingPrice"
                component="div"
                className="formik-error-message"
              />
              <div className="select-dates">
                <div className="date-div">
                  <label
                    className="formik-field-label date-label"
                    htmlFor="start-date"
                  >
                    Start date
                  </label>
                  <Field
                    value={props.startDate.value}
                    onChange={(e) => props.startDate.set(e.target.value)}
                    type="date"
                    name="startDate"
                    className="custom-formik-field date-field"
                  />
                  <ErrorMessage
                    name="startDate"
                    component="div"
                    className="formik-error-message"
                  />
                </div>
                <div className="date-div">
                  <label
                    className="formik-field-label date-label"
                    htmlFor="start-date"
                  >
                    Start date
                  </label>
                  <Field
                    value={props.endDate.value}
                    onChange={(e) => props.endDate.set(e.target.value)}
                    type="date"
                    name="endDate"
                    className="custom-formik-field date-field"
                  />
                  <ErrorMessage
                    name="endDate"
                    component="div"
                    className="formik-error-message"
                  />
                </div>
              </div>
              <div className="form-bottom-buttons">
                <Button type="white">CANCEL</Button>
                <div className="right-form-buttons">
                  <Button onClick={props.onBack}>BACK</Button>
                  <button className="button purple" type="submit">
                    NEXT
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

export default SetPrices;
