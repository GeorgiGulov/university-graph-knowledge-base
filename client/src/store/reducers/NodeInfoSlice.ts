import {createSlice, PayloadAction} from "@reduxjs/toolkit";

interface NodeInfoState {
    nodeInfoId: number
}

const initState: NodeInfoState = {
    nodeInfoId: 0
}

export const nodeInfoSlice = createSlice({
    name: 'nodeInfo',
    initialState: initState,
    reducers: {
        switchNodeInfo(state, action: PayloadAction<number>) {
            state.nodeInfoId = action.payload
        },
    }
})

export const nodeInfoReducer = nodeInfoSlice.reducer