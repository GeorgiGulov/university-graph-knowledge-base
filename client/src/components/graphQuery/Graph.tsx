import React, {useEffect, useState} from 'react';
import {Network} from "vis-network/peer/esm/vis-network";
import {DataSet} from "vis-data/peer/esm/vis-data";
import {Edge} from "vis-network/declarations/network/Network";
import {useAppDispatch, useAppSelector} from "../../hooks/redux";
import {graphSlice, IChangeElementLabel} from "../../store/reducers/GraphSlice";
import {nodeInfoSlice} from "../../store/reducers/NodeInfoSlice";
import MuRadioButton from "../UI/MuRadioButton";
import {INode} from "../../entity/graphQuery/INode";
import MyButton from "../UI/MyButton";
import {Pageable, queryApi, QueryExecute} from "../../services/QueryService";
import {GraphQueryDto} from "../../dto/queryDto/GraphQueryDto";
import MyInput from "../UI/MyInput";

const Graph = () => {

    const addNode = graphSlice.actions.addNode
    const addEdge = graphSlice.actions.addEdge
    const changeLabelNode = graphSlice.actions.changeLabelNode
    const changeLabelEdge = graphSlice.actions.changeLabelEdge


    const switchNodeInfo = nodeInfoSlice.actions.switchNodeInfo
    const listNodes = useAppSelector(state => state.graphReducer.nodes)
    const listEdges = useAppSelector(state => state.graphReducer.edges)

    const [labelElement, setLabelElement] = useState("")
    const [typeOperation, changeTypeOperation] = useState("none")
    const [network, setNetwork] = useState(null)


    const dispatch = useAppDispatch()
    const [getQuery] = queryApi.useFetchGraphQueryMutation()


    useEffect(() => {

        console.log("useEffectOnce")

        const nodes = new DataSet([
            ...listNodes
        ]);

        const edges = new DataSet([
            ...listEdges
        ]);

        // @ts-ignore
        const container: HTMLElement = document.getElementById("mynetwork");

        const data = {
            nodes,
            edges
        }

        const options = {
            width: "600px",
            height: "600px",
            nodes: {
                shape: 'dot'
            },
            edges: {
                smooth: false
            },
            interaction: {hover: true}
        }

        // @ts-ignore
        setNetwork(new Network(container, data, options))

    }, []);


    useEffect(() => {
        console.log("111", network)

        if(network != null) {
            const networkNew = network as Network
            console.log("2222")

            const options = {
                width: "600px",
                height: "600px",
                nodes: {
                    shape: 'dot',
                },
                edges: {
                    font: {
                        align: "top"
                    },
                    smooth: true, //сгибающиеся или прямые ребра
                    arrows: {
                        to: { enabled: true, scaleFactor: 1, type: "arrow" }
                    }
                },
                interaction: {hover: true},
                manipulation: {
                    enabled: false,
                    // @ts-ignore
                    addNode: function (data, callback) {
                        console.log('add', data)

                        data.label = "Пустая вершина"

                        const node: INode = {
                            id: data.id,
                            label: data.label,
                            property: []

                        }
                        // @ts-ignore
                        dispatch(addNode(node))
                        callback(data)

                        networkNew.disableEditMode()
                    },
                    // @ts-ignore
                    editNode: function (data, callback) {
                        data.label = labelElement
                        callback(data)

                        const changeLabel: IChangeElementLabel = {
                            id: data.id,
                            label: labelElement
                        }

                        dispatch(changeLabelNode(changeLabel))
                        networkNew.disableEditMode()
                    },
                    // @ts-ignore
                    editEdge: function (data, callback) {
                        data.label = labelElement
                        callback(data)

                        const changeLabel: IChangeElementLabel = {
                            id: data.id,
                            label: labelElement
                        }

                        dispatch(changeLabelEdge(changeLabel))
                        networkNew.disableEditMode()
                    },
                    // @ts-ignore
                    addEdge: function (data, callback) {
                        const id = Math.random() * 1000
                        const edge: Edge = {
                            id: id,
                            from: data.from,
                            to: data.to
                        }

                        // @ts-ignore
                        dispatch(addEdge(edge))
                        callback(data)

                        networkNew.disableEditMode()
                    }
                },
            }

            networkNew.off('click')
            networkNew.on('click', event => {

                switch (typeOperation) {
                    case "none":
                        break

                    case "addSearch":
                        if (event.nodes.length == 0) {
                            networkNew.addNodeMode()
                        }
                        break

                    case "addData":
                        if (event.nodes.length == 0) {
                            networkNew.addNodeMode()
                        }
                        break
                    default:
                        break
                }


            })

            networkNew.off('selectEdge')
            networkNew.on("selectEdge", params => {

                switch (typeOperation) {
                    case "none":
                        console.log(typeOperation)
                        break

                    case "editEdge":
                        console.log(typeOperation)
                        networkNew.editEdgeMode()
                        break

                    default:
                        break

                }
            })

            networkNew.off('selectNode')
            networkNew.on("selectNode", params => {

                switch (typeOperation) {
                    case "none":
                        console.log(typeOperation)
                        const id = params.nodes[0]
                        dispatch(switchNodeInfo(id))
                        break

                    case "editNode":
                        console.log(typeOperation)
                        networkNew.editNode()
                        break

                    case "addEdge":
                        networkNew.addEdgeMode()
                        break

                    default:
                        break
                }
            })

            networkNew.setOptions(options)

        }
    })

    {/*TODO: доделать запрос*/}
    return (
        <div>
            <MuRadioButton type={typeOperation} onChange={(str) => {
                changeTypeOperation(str)
            }}></MuRadioButton>


            <MyInput placeholder={"Имя вершины"} value={labelElement} onChange={(str)=>{
                setLabelElement(str)
            }
            }/>


            <MyButton description={"Выполнить запрос"} onClick={async () => {
                const pageable: Pageable = {
                    size: 0,
                    numberPage: 1
                }

                const queryGraph: GraphQueryDto = {
                    nodes: [],
                    edges: []
                }

                const query = {
                    query: queryGraph,
                    pageable: pageable
                } as QueryExecute

                await getQuery(query)

            }}/>

            <div id="mynetwork"></div>
        </div>

    );
};


export default Graph;