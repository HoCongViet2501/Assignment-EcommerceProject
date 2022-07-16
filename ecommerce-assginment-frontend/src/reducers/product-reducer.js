import {
  FETCH_PRODUCTS,
  FETCH_PRODUCT_BY_ID,
  FETCH_PRODUCTS_BY_GENDER,
  FETCH_PRODUCTS_BY_NAME,
  FETCH_PRODUCTS_BY_PRICE,
  FETCH_PRODUCTS_BY_CATEGORY,
  FETCH_PRODUCTS_BY_BRAND,
} from "../utils/constants/actions-types";

const initialState = {
  products: [],
  product: {},
  ratings: [],
};

const reducer = (state = initialState, action) => {
  const { type, payload } = action;

  switch (type) {
    case FETCH_PRODUCTS:
      return { ...state, products: payload };

    case FETCH_PRODUCT_BY_ID:
      return { ...state, product: payload, ratings: payload.ratings };

    case FETCH_PRODUCTS_BY_GENDER:
      return { ...state, products: payload };

    case FETCH_PRODUCTS_BY_NAME:
      return { ...state, products: payload };

    case FETCH_PRODUCTS_BY_PRICE:
      return { ...state, products: payload };

    case FETCH_PRODUCTS_BY_CATEGORY:
      return { ...state, products: payload };

    case FETCH_PRODUCTS_BY_BRAND:
      return { ...state, products: payload };
      
    default:
      return state;
  }
};

export default reducer;
