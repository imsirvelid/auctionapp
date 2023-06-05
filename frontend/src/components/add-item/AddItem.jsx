import React, {useEffect, useState} from "react";
import {Formik, Form, Field, ErrorMessage} from "formik";
import * as Yup from "yup";
import "./AddItem.css";
import DragAndDrop from "components/drag-and-drop/DragAndDrop";
import Button from "components/button/Button";
import {getParentCategories} from "api/Category";
import {getSubCategories} from "api/Category";

function AddItem(props) {
  const [errorMessage, setErrorMessage] = useState("");
  const [categories, setCategories] = useState([]);
  const [subCategories, setSubCategories] = useState([]);

  useEffect(() => {
    const getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };
    getCategories();
  }, []);

  useEffect(() => {
    if (!props.category.value) return; 
    const getSubCat = async (parentCategoryId) => {
      const res = await getSubCategories(parentCategoryId);
      setSubCategories(res);
    };
    getSubCat(props.category.value);
  }, [props.category.value]);

  const validationSchema = Yup.object().shape({
    name: Yup.string().required("Required"),
    category: Yup.number().required("Required"),
    subCategory: Yup.number().required("Required"),
    description: Yup.string().required("Required"),
  });

  const addItemSubmit = (values) => {
    if (props.files.value.length <= 2) {
      setErrorMessage("You have to upload at least 3 photos");
      return;
    }
    props.onNext();
  };

  return (
    <div className="container-55">
      <div className="form-container">
        <h2 className="form-title"> ADD ITEM </h2>
        {errorMessage && <p className="login-error-message">{errorMessage}</p>}
        <Formik
          enableReinitialize
          validationSchema={validationSchema}
          initialValues={{
            name: props.productName.value,
            category: props.category.value,
            subCategory: props.subCategory.value,
            description: props.description.value,
            files: props.files.value,
            featured: props.featured.value
          }}
          onSubmit={(values) => addItemSubmit(values)}
        >
          {() => (
            <Form className="add-item-form">
              <label className="formik-field-label" htmlFor="email">
                What do you sell?
              </label>
              <Field
                type="text"
                name="name"
                value={props.productName.value}
                placeholder="eg. Targeal 7.1 Surround Sound Gaming Headset for PS4"
                onChange={(e) => {
                  props.productName.set(e.target.value);
                }}
                className="custom-formik-field"
              />
              <ErrorMessage
                name="name"
                component="div"
                className="formik-error-message"
              />
              <div className="category-dropdowns">
                <Field
                  as="select"
                  name="category"
                  className="custom-formik-field category-dropdown"
                  value={props.category.value}
                  onChange={(e) => props.category.set(e.target.value)}
                >
                  <option value="0" disabled>
                    Select category
                  </option>
                  {categories.map((category, index) => (
                    <option key={index} value={category.id}>
                      {category.name}
                    </option>
                  ))}
                </Field>

                <Field
                  as="select"
                  name="subCategory"
                  className="custom-formik-field category-dropdown"
                  value={props.subCategory.value}
                  placeholder="Select subcategory"
                  onChange={(e) => props.subCategory.set(e.target.value)}
                >
                  <option value="0" disabled>
                    Select subcategory
                  </option>
                  {subCategories.map((category, index) => (
                    <option key={index} value={category.id}>
                      {category.name}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  name="category"
                  component="div"
                  className="formik-error-message smaller"
                />
                <ErrorMessage
                  name="subCategory"
                  component="div"
                  className="formik-error-message smaller"
                />
              </div>
              <label className="formik-field-label" htmlFor="password">
                Description
              </label>
              <textarea
                value={props.description.value}
                onChange={(e) => props.description.set(e.target.value)}
                name="description"
                className="custom-formik-field custom-text-area"
              />
              <ErrorMessage
                name="description"
                component="div"
                className="formik-error-message"
              />
              <DragAndDrop files={props.files} minImages={3} featured={{value: props.featured.value, set: props.featured.set}}/>
              <ErrorMessage
                name="files"
                component="div"
                className="formik-error-message"
              />
              <div className="add-item-bottom-buttons">
                <Button type="white">CANCEL</Button>
                <Button buttonType="submit" className="button purple">
                  NEXT
                </Button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default AddItem;
