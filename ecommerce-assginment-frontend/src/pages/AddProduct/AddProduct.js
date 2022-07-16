import React, {Component, useEffect, useState} from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {faPlusSquare} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import ToastShow from "../../component/Toasts/ToastShow";
import AccountNavbar from "../../component/AccountNavbar/AccountNavbar";
import {addProduct, formReset} from "../../actions/admin-actions";
import {API_ADMIN_BASE_URL} from "../../utils/constants/url";
import axios from "axios";

class AddProduct extends Component {
    initialState = {
        name: "",
        volume: "",
        price: "",
        quantity: "",
        createdDate: "",
        updatedDate: "",
        categoryId: "",
        brandId: "",
        gender: "",
        file: null
    };
    const
    brandGet = axios.get({
        url: API_ADMIN_BASE_URL + "/brands"
    });
    state = {
        ...this.initialState,
        showToast: false
    };

    componentDidMount() {
        this.props.formReset();
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        const {
            name, volume, price, quantity, createdDate, updatedDate, categoryId, brandId, gender, file
        } = this.state;

        const bodyFormData = new FormData();

        bodyFormData.append("name", name);
        bodyFormData.append("quantity", quantity);
        bodyFormData.append("createdDate", createdDate);
        bodyFormData.append("updatedDate", updatedDate);
        bodyFormData.append("categoryId", categoryId);
        bodyFormData.append("brandId", brandId);
        bodyFormData.append("volume", volume);
        bodyFormData.append("gender", gender);
        bodyFormData.append("file", file);
        bodyFormData.append("price", price);

        this.props.addProduct(bodyFormData)
            .then(() => {
                if (this.props.success) {
                    this.setState({
                        ...this.initialState,
                        showToast: true
                    });
                    setTimeout(() => this.setState({showToast: false}), 5000);
                    window.scrollTo(0, 0);
                }
            });
    };

    handleFileChange = (event) => {
        this.setState({
            file: event.target.files[0]
        });
    };

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    };

    render() {
        const {
            name, volume, price, quantity, categoryId, brandId, status, gender, file, showToast
        } = this.state;

        const {
            nameError, volumeError, quantityError, categoryIdError, brandIdError,
            genderError, statusError, fileError, priceError
        } = this.props.errors;

        return (
            <div>
                <AccountNavbar/>
                <div className="container" style={{"display": showToast ? "block" : "none"}}>
                    <ToastShow showToast={showToast} message={"Product successfully added!"}/>
                </div>
                <div className="container mt-5">
                    <h4><FontAwesomeIcon className="mr-2" icon={faPlusSquare}/>Add perfume</h4>
                    <br/>
                    <form onSubmit={this.onFormSubmit}>
                        <div className="form row">
                            <div className="col">
                                <label>Product Name: </label>
                                <input
                                    type="text"
                                    className={nameError ? "form-control is-invalid" : "form-control"}
                                    name="name"
                                    value={name}
                                    placeholder="Enter the product name"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{nameError}</div>
                            </div>
                            <div className="col">
                                <label>Volume: </label>
                                <input
                                    type="text"
                                    className={volumeError ? "form-control is-invalid" : "form-control"}
                                    name="name"
                                    value={volume}
                                    placeholder="Enter the volume"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{volumeError}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Price: </label>
                                <input
                                    type="text"
                                    className={priceError ? "form-control is-invalid" : "form-control"}
                                    name="price"
                                    value={price}
                                    placeholder="Enter the price"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{priceError}</div>
                            </div>
                            <div className="col" style={{marginTop: "35px"}}>
                                <input type="file"
                                       name="file"
                                       onChange={this.handleFileChange}/>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Quantity: </label>
                                <input
                                    type="text"
                                    className={quantityError ? "form-control is-invalid" : "form-control"}
                                    name="quantity"
                                    value={quantity}
                                    placeholder="Enter quantity"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{quantityError}</div>
                            </div>
                            <div className="col">
                                <label>Status: </label>
                                <input
                                    type="text"
                                    className={statusError ? "form-control is-invalid" : "form-control"}
                                    name="status"
                                    value={status}
                                    placeholder="Enter status: "
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{status}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Category: </label>
                                <input
                                    type="text"
                                    className={categoryIdError ? "form-control is-invalid" : "form-control"}
                                    name="categoryID"
                                    value={categoryId}
                                    placeholder="Enter the category id"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{categoryId}</div>
                            </div>
                            <div className="col">
                                <label>Brand id: </label>
                                <input
                                    type="text"
                                    className={brandIdError ? "form-control is-invalid" : "form-control"}
                                    name="brandID"
                                    value={brandId}
                                    placeholder="Enter the brand id"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{brandId}</div>
                            </div>
                        </div>
                        <div className="form row mt-3">
                            <div className="col">
                                <label>Gender: </label>
                                <input
                                    type="text"
                                    className={genderError ? "form-control is-invalid" : "form-control"}
                                    name="gender"
                                    value={gender}
                                    placeholder="Enter the gender"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{genderError}</div>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-dark mt-3">
                            <FontAwesomeIcon className="mr-2" icon={faPlusSquare}/>Add
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

AddProduct.propTypes = {
    addProduct: PropTypes.func.isRequired,
    formReset: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    success: PropTypes.bool.isRequired
};

const mapStateToProps = (state) => ({
    errors: state.admin.errors,
    success: state.admin.success,
});

export default connect(mapStateToProps, {addProduct, formReset})(AddProduct);
