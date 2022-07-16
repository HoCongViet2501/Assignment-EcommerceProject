import React from 'react';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faShoppingBag} from "@fortawesome/free-solid-svg-icons";

import AccountNavbar from "../AccountNavbar/AccountNavbar";

const OrdersTable = ({orders}) => {
    return (
        <div>
            <AccountNavbar/>
            <div className="container mt-5">
                <h4><FontAwesomeIcon className="ml-2 mr-2" icon={faShoppingBag}/> List of all orders</h4>
                <table className="table mt-4">
                    <thead>
                    <tr>
                        <th scope="col">Order №</th>
                        <th scope="col">Date</th>
                        <th scope="col">Customer</th>
                        <th scope="col">Address</th>
                        <th scope="col">Post index</th>
                        <th scope="col">Products</th>
                        <th scope="col">Sum, $</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default OrdersTable;