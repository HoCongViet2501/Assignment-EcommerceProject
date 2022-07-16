import axios from "axios";

import {
  FETCH_PRODUCTS,
  FETCH_PRODUCTS_BY_GENDER,
  FETCH_PRODUCTS_BY_NAME,
  FETCH_PRODUCTS_BY_PRICE,
  FETCH_PRODUCTS_BY_BRAND, FETCH_PRODUCTS_BY_CATEGORY,
} from "../utils/constants/actions-types";
import { API_USER_BASE_URL } from "../utils/constants/url";

export const fetchProducts = () => async (dispatch) => {
  const response = await axios.get(API_USER_BASE_URL + "/products");

  dispatch({
    type: FETCH_PRODUCTS,
    payload: response.data,
  });
};

export const fetchProductsByBrand = (name) => async (dispatch) => {
  const response = await axios.get(
    API_USER_BASE_URL + "/products/brand/" + name
  );

  dispatch({
    type: FETCH_PRODUCTS_BY_BRAND,
    payload: response.data,
  });
};
export const fetchProductsByCategory = (name) => async (dispatch) => {
  const response = await axios.get(
    API_USER_BASE_URL + "/products/category/" + name
  );

  dispatch({
    type: FETCH_PRODUCTS_BY_CATEGORY,
    payload: response.data,
  });
};
export const fetchProductsByGender = (gender) => async (dispatch) => {
  const response = await axios.get(
    API_USER_BASE_URL + "/products/gender/" + gender
  );

  dispatch({
    type: FETCH_PRODUCTS_BY_GENDER,
    payload: response.data,
  });
};

export const fetchProductsByName = (name) => async (dispatch) => {
  const response = await axios.get(
    API_USER_BASE_URL + "/products/name/" + name
  );

  dispatch({
    type: FETCH_PRODUCTS_BY_NAME,
    payload: response.data,
  });
};

export const fetchProductsByPrice = (start, end) => async (dispatch) => {
  const response = await axios.get(
    API_USER_BASE_URL + "/products/price/" + start + "/" + end
  );

  dispatch({
    type: FETCH_PRODUCTS_BY_PRICE,
    payload: response.data,
  });
};
