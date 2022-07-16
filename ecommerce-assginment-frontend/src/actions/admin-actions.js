import axios from "axios";

import {
  PRODUCT_ADDED_SUCCESS,
  PRODUCT_UPDATED_SUCCESS,
  PRODUCT_ADDED_FAILURE,
  PRODUCT_UPDATED_FAILURE,
  FETCH_PRODUCT_BY_ID,
  FETCH_USER_SUCCESS,
  FETCH_ALL_USERS_SUCCESS,
  FETCH_ALL_USERS_ORDERS_SUCCESS,
  FORM_RESET,
} from "../utils/constants/actions-types";
import { API_ADMIN_BASE_URL} from "../utils/constants/url";

export const addProduct = (data) => async (dispatch) => {
  try {
    const response = await axios({
      method: "POST",
      url: API_ADMIN_BASE_URL + "/products",
      data: data,
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: localStorage.getItem("token"),
      },
    });

    dispatch({
      type: PRODUCT_ADDED_SUCCESS,
      payload: response.data,
    });
  } catch (error) {
    dispatch({
      type: PRODUCT_ADDED_FAILURE,
      payload: error.response.data,
    });
  }
};

export const updateProduct = (id, data, history) => async (dispatch) => {
  try {
    const response = await axios({
      method: "PUT",
      url: API_ADMIN_BASE_URL + "/products/" + id,
      data: data,
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: localStorage.getItem("token"),
      },
    });

    history.push("/account");

    dispatch({
      type: PRODUCT_UPDATED_SUCCESS,
      payload: response.data,
    });
  } catch (error) {
    dispatch({
      type: PRODUCT_UPDATED_FAILURE,
      payload: error.response.data,
    });
  }
};

export const fetchAllUsersOrders = () => async (dispatch) => {
  const response = await axios({
    method: "GET",
    url: API_ADMIN_BASE_URL + "/users/orders",
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });

  dispatch({
    type: FETCH_ALL_USERS_ORDERS_SUCCESS,
    payload: response.data,
  });
};

export const fetchAllUsers = () => async (dispatch) => {
  const response = await axios({
    method: "GET",
    url: API_ADMIN_BASE_URL + "/users",
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });

  dispatch({
    type: FETCH_ALL_USERS_SUCCESS,
    payload: response.data,
  });
};

export const fetchProductById = (id) => async (dispatch) => {
  const response = await axios({
    method: "GET",
    url: API_ADMIN_BASE_URL + "/products/" + id,
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });
  dispatch({
    type: FETCH_PRODUCT_BY_ID,
    payload: response.data,
  });
};

export const fetchUser = (id) => async (dispatch) => {
  const response = await axios({
    method: "GET",
    url: API_ADMIN_BASE_URL + "/users" + id,
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });
  dispatch({
    type: FETCH_USER_SUCCESS,
    payload: response.data,
  });
};

export const formReset = () => async (dispatch) => {
  dispatch({
    type: FORM_RESET,
  });
};
