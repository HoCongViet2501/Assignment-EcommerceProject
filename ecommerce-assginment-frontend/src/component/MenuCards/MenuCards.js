import React from 'react';

import usePagination from "../Pagination/usePagination";
import ProductCardItem from "../ProductCardItem/ProductCardItem";
import PaginationItem from "../Pagination/PaginationItem";
import SearchForm from "../SearchForm/SearchForm";

const MenuCards = ({data, itemsPerPage, startFrom, searchByData}) => {
    const {slicedData, pagination, prevPage, nextPage, changePage, setFilteredData, setSearching} = usePagination({
        itemsPerPage,
        data,
        startFrom
    });

    return (
        <div className="container">
            <div className="container-fluid row mt-5 ml-2">
                <SearchForm
                    data={data}
                    searchByData={searchByData}
                    setFilteredData={setFilteredData}
                    setSearching={setSearching}/>
            </div>
            <div className="row mt-3 ml-2">
                <div className="container-fluid">
                    <PaginationItem
                        pagination={pagination}
                        prevPage={prevPage}
                        changePage={changePage}
                        nextPage={nextPage}/>
                    <div className="row">
                        {slicedData.map((product) => {
                            return (
                                <ProductCardItem
                                    product={product}
                                    colSize={3}
                                    link={"/products"}
                                    btnName={"SHOW MORE"}/>
                            );
                        })}
                    </div>
                    <PaginationItem
                        pagination={pagination}
                        prevPage={prevPage}
                        changePage={changePage}
                        nextPage={nextPage}/>
                </div>
            </div>
        </div>
    );
}

export default MenuCards;