import {useAppDispatch, useAppSelector} from "../../hooks/redux";
import {graphSlice} from "../../store/reducers/GraphSlice";
import {IProperty} from "../../entity/graphQuery/IProperty";
import {INode} from "../../entity/graphQuery/INode";
import PropertyInfoInput from "./PropertyInfoInput";


const NodeInfo = () => {
    const nodeInfoId = useAppSelector(state => state.nodeInfoReducer.nodeInfoId)
    const listNodes = useAppSelector(state => state.graphReducer.nodes)
    const changeNodeLamda = graphSlice.actions.changeNode
    const dispatch = useAppDispatch()

    console.log("nodeInfoId", nodeInfoId)
    console.log("listNodes", listNodes)

    const nodeInfo = listNodes.find((node) => node.id == nodeInfoId)
    console.log("nodeInfo:", nodeInfo)

    return (
        <div>
            <div>{nodeInfo?.label}</div>
            {
                nodeInfo?.property.map((it) => <div key={it.id}>{it.label}: {it.value}</div>)
            }

            <PropertyInfoInput onClick={(key, value) => {

                const property: IProperty = {
                    id: (Math.random() * 1000).toString(),
                    label: key,
                    value: value
                }

                if (nodeInfo != null) {

                    const changeNode: INode = {
                        id: nodeInfo.id,
                        label: nodeInfo.label,
                        property: [...nodeInfo.property].concat(property)
                    }

                    console.log("changeNode", changeNode)
                    dispatch(changeNodeLamda(changeNode))
                }
            }}/>

        </div>
    );
};

export default NodeInfo;