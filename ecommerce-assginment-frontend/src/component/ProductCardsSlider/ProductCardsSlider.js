import React, {Component} from 'react';
import Carousel from "react-bootstrap/Carousel";
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import PropTypes from "prop-types";

import {IMG_URL} from "../../utils/constants/url";
import {fetchProducts} from "../../actions/product-actions"
import "./ProductCardsSlider.css";

class ProductCardsSlider extends Component {
    componentDidMount() {
        this.props.fetchProducts();
    }

    addCarouselItems = (array, counter) => {
        const productsId = [23, 24, 25, 27, 26, 28, 29, 30, 32, 22, 37, 36];

        return (
            <Carousel.Item>
                <div className="card-deck">
                    {array.map((product) => {
                        for (let i = counter; i < counter + 4; i++) {
                            if (product.id === productsId[i]) {
                                return (
                                    <div className="card" key={product.id}>
                                        <img className="d-block mx-auto w-50" src={IMG_URL + `${product.id}`}
                                             alt=''/>
                                        <div className="card-body text-center">
                                            <h5>{product.name}</h5>
                                            <h6>$<span>{product.price}</span>.00</h6>
                                            <Link to={`/product/${product.id}`}>
                                            <span className="btn btn-dark">
                                                SHOW MORE
                                            </span>
                                            </Link>
                                        </div>
                                    </div>
                                );
                            }
                        }
                        return null;
                    })}
                </div>
            </Carousel.Item>
        );
    }

    render() {
        const {products} = this.props;
        const settings = {controls: false}

        return (
            <div>
                <div className="container text-center my-3">
                    <h3>PERSONALLY RECOMMENDED</h3>
                </div>
                <div className="container mt-5" id="indicators">
                    <form method="get" action="/">
                        <Carousel {...settings}>
                            {this.addCarouselItems(products, 0)}
                            {this.addCarouselItems(products, 4)}
                            {this.addCarouselItems(products, 8)}
                        </Carousel>
                    </form>
                </div>
            </div>
        );
    }
}

ProductCardsSlider.propTypes = {
    fetchProducts: PropTypes.func.isRequired,
    products: PropTypes.array.isRequired
};

const mapStateToProps = (state) => ({
    products: state.product.products,
});

export default connect(mapStateToProps, {fetchProducts})(ProductCardsSlider);
