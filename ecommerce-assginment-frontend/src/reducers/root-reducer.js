import {combineReducers} from "redux";

import productReducer from "./product-reducer";
import authReducer from "../reducers/auth-reducer";
import cartReducer from "../reducers/cart-reducer";
import adminReducer from "../reducers/admin-reducer";
import userReducer from "../reducers/user-reducer";

const rootReducer = combineReducers({
    product: productReducer,
    auth: authReducer,
    cart: cartReducer,
    admin: adminReducer,
    user: userReducer
});

export default rootReducer;