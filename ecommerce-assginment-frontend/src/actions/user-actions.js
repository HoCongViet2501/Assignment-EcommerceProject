import axios from "axios";

import {
  USER_UPDATED_SUCCESS,
  USER_ADDED_RATING_SUCCESS,
  USER_ADDED_RATING_FAILURE,
} from "../utils/constants/actions-types";
import { API_USER_BASE_URL } from "../utils/constants/url";

export const updateUserInfo = (userData, history) => async (dispatch) => {
  axios({
    method: "PUT",
    url: API_USER_BASE_URL + "/users/edit",
    data: userData,
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });

  dispatch({
    type: USER_UPDATED_SUCCESS,
  });

  history.push("/account");
};

export const addRatingToProduct = (data) => async (dispatch) => {
  try {
    await axios.post(API_USER_BASE_URL + "/users/rating/", data);

    dispatch({
      type: USER_ADDED_RATING_SUCCESS,
    });

    window.location.reload();
  } catch (error) {
    dispatch({
      type: USER_ADDED_RATING_FAILURE,
      payload: error.response.data,
    });
  }
};
