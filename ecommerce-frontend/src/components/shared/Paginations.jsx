import { Pagination } from "@mui/material";
import { useNavigate, useSearchParams, useLocation } from "react-router-dom";

const Paginations = ({numberOfPages, totalOfProducts}) => {
    const [searchParams] = useSearchParams();
    const pathName = useLocation().pathName;
    const params = new URLSearchParams(searchParams);
    const navigate = useNavigate();
    const paramValue = searchParams.get("page") ? Number(searchParams.get("page")) : 1;
    const onChangeHandler = (event, value) => {
        params.set("page", value.toString())
        navigate(`${pathName}?${params}`);
    }

    return (
        <Pagination
            count={numberOfPages} 
            page={paramValue}
            defaultPage={1} 
            siblingCount={0} 
            boundaryCount={2}
            shape="rounded"
            onChange={onChangeHandler} />
    );
}

export default Paginations;