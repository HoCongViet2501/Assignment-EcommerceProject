import React, {Component} from 'react';
import PropTypes from "prop-types";
import {connect} from "react-redux";

import EditPerfumes from "./EditProducts";
import {fetchProducts} from "../../actions/product-actions";

class EditProductList extends Component {

    componentDidMount() {
        this.props.fetchProducts();
    }

    render() {
        const {products} = this.props;
        const itemsPerPage = 24;
        const searchByData = [
            {label: 'name', value: 'name'},
            {label: 'brand', value: 'brandName'},
            {label: 'category', value: 'categoryName'},
            {label: 'Gender', value: 'gender'}
        ];

        return (
            <EditPerfumes
                data={products}
                itemsPerPage={itemsPerPage}
                searchByData={searchByData}/>
        );
    }
}

EditProductList.propTypes = {
    fetchProducts: PropTypes.func.isRequired,
    products: PropTypes.array.isRequired
};

const mapStateToProps = (state) => ({
    perfumes: state.perfume.perfumes
});

export default connect(mapStateToProps, {fetchProducts})(EditProductList);
