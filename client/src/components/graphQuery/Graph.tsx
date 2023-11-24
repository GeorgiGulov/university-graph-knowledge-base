import React, {useEffect, useState} from 'react';
import {Network} from "vis-network/peer/esm/vis-network";
import {DataSet} from "vis-data/peer/esm/vis-data";
import {Data} from "vis-network/dist/types";
import {PartItem} from "vis-data/declarations/data-interface";
import {Edge} from "vis-network/declarations/network/Network";
import {useAppDispatch, useAppSelector} from "../../hooks/redux";
import {graphSlice} from "../../store/reducers/GraphSlice";
import {nodeInfoSlice} from "../../store/reducers/NodeInfoSlice";
import MuRadioButton from "../UI/MuRadioButton";
import {INode} from "../../entity/graphQuery/INode";
import {IEdge} from "../../entity/graphQuery/IEdge";
import MyButton from "../UI/MyButton";
import {Pageable, queryApi, QueryExecute} from "../../services/QueryService";
import {GraphQueryDto} from "../../dto/queryDto/GraphQueryDto";
import MyInput from "../UI/MyInput";

let typeOperation = "none"

function changeTypeOperation(str: string) {
    typeOperation = str
}

const Graph = () => {

    const addNode = graphSlice.actions.addNode
    const addEdge = graphSlice.actions.addEdge
    const switchNodeInfo = nodeInfoSlice.actions.switchNodeInfo
    const listNodes = useAppSelector(state => state.graphReducer.nodes)
    const listEdges = useAppSelector(state => state.graphReducer.edges)

    const [labelNode, setLabelNode] = useState("")

    const dispatch = useAppDispatch()


    const [getQuery] = queryApi.useFetchGraphQueryMutation()

    useEffect(() => {

        const nodes = new DataSet([
            ...listNodes
        ]);


        const edges = new DataSet([
            ...listEdges
        ]);

        // @ts-ignore
        const container: HTMLElement = document.getElementById('mynetwork');

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
            interaction: {hover: true},
            manipulation: {
                enabled: false,
                // @ts-ignore
                addNode: function (data, callback) {
                    console.log('add', data)

                    data.label = "Новая вершина"

                    const node: INode = {
                        id: data.id,
                        label: data.label,
                        property: []

                    }
                    // @ts-ignore
                    dispatch(addNode(node))
                    callback(data);
                    network.disableEditMode()
                },
                // @ts-ignore
                editNode: function (data, callback) {
                    // filling in the popup DOM elements
                    console.log('edit', data);
                    data.label = labelNode
                    callback(data);
                    network.disableEditMode()
                },
                // @ts-ignore
                addEdge: function (data, callback) {
                    console.log('add edge', data)

                    const id = Math.random() * 1000
                    const edge: Edge = {
                        id: id,
                        from: data.from,
                        to: data.to
                    }

                    // @ts-ignore
                    dispatch(addEdge(edge))
                    callback(data)
                    network.disableEditMode()
                }
            },
        }


        // @ts-ignore
        const network = new Network(container, data, options)

        network.on('click', event => {

            switch (typeOperation) {
                case "none":
                    break

                case "addSearch":
                    if (event.nodes.length == 0) {
                        network.addNodeMode()
                    }
                    break

                case "addData":
                    if (event.nodes.length == 0) {
                        network.addNodeMode()
                    }
                    break
                default:
                    break
            }


        });


        network.on("selectNode", params => {

            switch (typeOperation) {
                case "none":
                    console.log('click to node', params)
                    network.editNode()
                    break

                case "addEdge":
                    network.addEdgeMode()
                    break

                default:
                    const id = params.nodes[0]
                    dispatch(switchNodeInfo(id))
                    break
            }


        });


    }, []);


    return (
        <div>
            <MuRadioButton type={typeOperation} onChange={(str) => {
                changeTypeOperation(str)
            }}></MuRadioButton>

            <MyInput placeholder={"Имя вершины"} value={labelNode} onChange={(str)=>{
                setLabelNode(str)
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