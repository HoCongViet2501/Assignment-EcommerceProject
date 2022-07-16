import React, {Component} from "react";
import {Route} from "react-router-dom";
import {connect} from "react-redux";
import PropTypes from "prop-types";

import Checkbox from "../../component/CheckBox/Checkbox";
import CheckboxRadio from "../../component/CheckboxRadio/CheckboxRadio";
import MenuCards from "../../component/MenuCards/MenuCards";
import {brands, gender, price} from "./MenuData";
import {
    fetchProducts,
    fetchProductsByBrand,
    fetchProductsByGender,
    fetchProductsByPrice,
} from "../../actions/product-actions.js";
import "./MenuStyle.css";

class Menu extends Component {
    state = {
        filterParams: {
            brands: [],
            genders: [],
            prices: []
        }
    };

    componentDidMount() {
        const productData = this.props.location.state.id;

        if (productData === "Female" || productData === "Fale") {
            this.props.fetchProductsByGender({productGender: productData});
            window.scrollTo(0, 0);
        } else if (productData === "all") {
            this.props.fetchProducts();
            window.scrollTo(0, 0);
        } else if (productData) {
            this.props.fetchProductsByBrand({brands: productData});
            window.scrollTo(0, 0);
        }
    }

    handlePrice = (value) => {
        const data = price;
        let array = [];

        for (let key in data) {
            if (data[key].id === parseInt(value, 10)) {
                array = data[key].array;
            }
        }

        return array
    };

    handleFilters = (filters, category) => {
        const newFilters = this.state.filterParams
        newFilters[category] = filters

        if (category === "prices") {
            newFilters[category] = this.handlePrice(filters)
        }

        this.getProducts(newFilters)
        this.setState(newFilters);
    };

    render() {
        const {products} = this.props;

        return (
            <div className="container d-flex">
                <nav id="sidebar">
                    <div className="sidebar-header">
                        <h3>Products</h3>
                    </div>
                    <ul className="list-unstyled components">
                        <h5>Brand</h5>
                        <li className="active mb-2" id="homeSubmenu">
                            <Checkbox list={brands}
                                      handleFilters={(filters) => this.handleFilters(filters, "brands")}/>
                        </li>
                        <h5>Gender</h5>
                        <li className="active mb-2">
                            <Checkbox list={gender}
                                      handleFilters={(filters) => this.handleFilters(filters, "genders")}/>
                        </li>
                        <h5>Price</h5>
                        <li className="active mb-2">
                            <CheckboxRadio list={price}
                                        handleFilters={(filters) => this.handleFilters(filters, "prices")}/>
                        </li>
                    </ul>
                </nav>
                <Route exact component={() => <MenuCards data={products} itemsPerPage={16} searchByData={[
                    {label: 'Brand', value: 'brand'},
                    {label: 'Category', value: 'category'},
                    ]}/>}/>
            </div>
        );
    }
}

Menu.propTypes = {
    fetchProducts: PropTypes.func.isRequired,
    fetchProductsByBrand: PropTypes.func.isRequired,
    fetchProductsByGender: PropTypes.func.isRequired,
    fetchProductsByPrice:PropTypes.func.isRequired,
    products: PropTypes.array.isRequired
};

const mapStateToProps = (state) => ({
    products: state.product.products,
});

export default connect(mapStateToProps, {
    fetchProducts,
    fetchProductsByGender,
    fetchProductsByPrice,
    fetchProductsByBrand,
})(Menu);
