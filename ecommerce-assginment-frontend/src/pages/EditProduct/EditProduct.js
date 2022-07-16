import React, {Component} from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit} from "@fortawesome/free-solid-svg-icons";

import AccountNavbar from "../../component/AccountNavbar/AccountNavbar";
import {updateProduct, formReset, fetchProductById} from "../../actions/admin-actions";

class EditProduct extends Component {
    state = {
        id: "",
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

    componentDidMount() {
        this.props.fetchProductById(this.props.match.params.id);
        this.props.formReset();
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            ...nextProps.product
        });
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        const {
            id,name, volume, price, quantity, createdDate, updatedDate, categoryId, brandId, gender
        } = this.state;

        const bodyFormData = new FormData();

        bodyFormData.append("id", id);
        bodyFormData.append("name", name);
        bodyFormData.append("price", price);
        bodyFormData.append("quantity", quantity);
        bodyFormData.append("createdDate", createdDate);
        bodyFormData.append("updatedDate", updatedDate);
        bodyFormData.append("categoryId", categoryId);
        bodyFormData.append("brandId", brandId);
        bodyFormData.append("volume", volume);
        bodyFormData.append("gender", gender);

        this.props.updateProduct(bodyFormData, this.props.history);
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
            name, volume, price, quantity, categoryId, brandId, status, gender
        } = this.state;

        const {
            nameError, volumeError, quantityError, categoryIdError, brandIdError,
            genderError, statusError, priceError
        } = this.props.errors;

        return (
            <div>
                <AccountNavbar/>
                <div className="container mt-5">
                    <h4><FontAwesomeIcon className="mr-2" icon={faEdit}/>Edit perfume</h4>
                    <form onSubmit={this.onFormSubmit}>
                        <div className="col-md-5 mb-5 mt-5">
                            <img alt=""
                                 className="rounded mx-auto w-100 mb-2"/>
                            <input type="file" name="file" onChange={this.handleFileChange}/>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Product name: </label>
                            <div className="col-sm-6">
                                <input
                                    type="text"
                                    className={nameError ? "form-control is-invalid" : "form-control"}
                                    name="name"
                                    value={name}
                                    placeholder="Enter the product name"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{nameError}</div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Brand: </label>
                            <div className="col-sm-6">
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
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Release year: </label>
                            <div className="col-sm-6">
                                <input
                                    type="text"
                                    className={priceError ? "form-control is-invalid" : "form-control"}
                                    name="price"
                                    value={price}
                                    placeholder="Enter the price"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{priceError}</div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Manufacturer country: </label>
                            <div className="col-sm-6">
                                <input
                                    type="text"
                                    className={quantityError ? "form-control is-invalid" : "form-control"}
                                    name="quantity"
                                    value={quantity}
                                    placeholder="Enter quantity"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{quantityError}</div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Perfume type: </label>
                            <div className="col-sm-6">
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
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Volume: </label>
                            <div className="col-sm-6">
                                <input
                                    type="text"
                                    className={categoryIdError ? "form-control is-invalid" : "form-control"}
                                    name="categoryID"
                                    value={categoryId}
                                    placeholder="Enter the category id"
                                    onChange={this.handleInputChange}/>
                                <div className="invalid-feedback">{categoryId}</div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Gender: </label>
                            <div className="col-sm-6">
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
                        <div className="form-group row">
                            <label className="col-sm-2 col-form-label">Top notes: </label>
                            <div className="col-sm-6">
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
                        <button type="submit" className="btn btn-dark">
                            <FontAwesomeIcon className="mr-2" icon={faEdit}/>Edit
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

EditProduct.propTypes = {
    updateProduct: PropTypes.func.isRequired,
    fetchProduct: PropTypes.func.isRequired,
    formReset: PropTypes.func.isRequired,
    admin: PropTypes.object.isRequired,
    product: PropTypes.object.isRequired
};

const mapStateToProps = (state) => ({
    errors: state.admin.errors,
    product: state.product.product
});

const mapDispatchToProps = (dispatch) => {
    return {
        updateProduct: (data, history) => dispatch(updateProduct(data, history)),
        fetchProduct: (id) => dispatch(fetchProductById(id)),
        formReset: () => dispatch(formReset())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(EditProduct);