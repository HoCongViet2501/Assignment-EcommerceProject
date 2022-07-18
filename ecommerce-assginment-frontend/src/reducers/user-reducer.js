import {
    USER_UPDATED_SUCCESS,
    USER_ADDED_RATING_SUCCESS,
    USER_ADDED_RATING_FAILURE,
} from "../utils/constants/actions-types";

const initialState = {
    success: false,
    errors: {}
};

const reducer = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case USER_UPDATED_SUCCESS:
            return {...state, success: true};

        case USER_ADDED_RATING_SUCCESS:
            return {...state, errors: {}};

        case USER_ADDED_RATING_FAILURE:
            return {...state, errors: payload};

        default:
            return state;
    }
};

export default reducer;