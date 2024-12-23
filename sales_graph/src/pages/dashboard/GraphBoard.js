import React, {memo} from "react";
import styled from 'styled-components';

import SalesGraph from "./SalesGraph";

const GraphBoardContainer = styled.div`

`;

const GraphBoard = memo( () => {
    return(
        <GraphBoardContainer>
            <SalesGraph />
        </GraphBoardContainer>
    );
} );

export default GraphBoard;