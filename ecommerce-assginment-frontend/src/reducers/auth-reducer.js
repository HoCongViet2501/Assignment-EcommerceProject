import {
    LOGIN_SUCCESS,
    FORM_RESET,
    REGISTER_SUCCESS,
    LOGIN_FAILURE,
    REGISTER_FAILURE,
    LOGOUT_SUCCESS,
} from "../utils/constants/actions-types";

const initialState = {
    user: {},
    isLoggedIn: false,
    isRegistered: false,
    success: "",
    error: "",
    errors: {}
};

const reducer = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case LOGIN_SUCCESS:
            return {...state, isLoggedIn: true};

        case LOGIN_FAILURE:
            return {...state, error: payload};

        case REGISTER_SUCCESS:
            return {...state, isRegistered: true};

        case REGISTER_FAILURE:
            return {...state, errors: payload};

        case LOGOUT_SUCCESS:
            return {...state, isLoggedIn: false, user: {}};

        case FORM_RESET:
            return {...state, error: "", errors: {}, success: "", isRegistered: false};

        default:
            return state;
    }
};

export default reducer;

