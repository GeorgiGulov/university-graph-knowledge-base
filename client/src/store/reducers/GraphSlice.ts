import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {INode} from "../../entity/graphQuery/INode";
import {IEdge} from "../../entity/graphQuery/IEdge";
import {IGraph} from "../../entity/graphQuery/IGraph";


const initState: IGraph = {
    nodes: [
        {
            id: 1,
            label: 'Студент',
            property: [
                {
                    id: 1,
                    label: "ФИО",
                    value: "Гулов Георгий Витальевич"
                },
                {
                    id: 2,
                    label: "Возраст",
                    value: "21"
                }
            ]
        },
        {
            id: 2,
            label: 'Преподаватель',
            property: [
                {
                    id: 3,
                    label: "ФИО",
                    value: "Коромысличенко Владислав Николаевич"
                },
                {
                    id: 4,
                    label: "Возраст",
                    value: "45"
                }
            ]
        },
        {
            id: 3,
            label: 'Предмет',
            property: [
                {
                    id: 5,
                    label: "Название",
                    value: "Основы программирования"
                },
            ]
        },
        {
            id: 4,
            label: 'Лабораторная',
            property: [
                {
                    id: 6,
                    label: "Номер",
                    value: "1"
                },
                {
                    id: 7,
                    label: "Название",
                    value: "Введение в JVM"
                },
                {
                    id: 8,
                    label: "Вариант",
                    value: "14"
                },
            ]
        },
        {
            id: 5,
            label: 'Лабораторная',
            property: [
                {
                    id: 9,
                    label: "Номер",
                    value: "2"
                },
                {
                    id: 10,
                    label: "Название",
                    value: "Основы синтаксиса"
                },
                {
                    id: 11,
                    label: "Вариант",
                    value: "14"
                },
            ]
        }
    ],
    edges: [
        {id: 1, label: 'обучается у', from: 1, to: 2,},
        {id: 2, label: 'изучает', from: 1, to: 3,},
        {id: 3, label: 'выполнил', from: 1, to: 4,},
        {id: 4, label: 'выполнил', from: 1, to: 5,}
    ]
}

export const graphSlice = createSlice({
    name: 'queryGraph',
    initialState: initState,
    reducers: {
        addNode(state, action: PayloadAction<INode>) {
            state.nodes = state.nodes.concat(action.payload)

        },
        changeNode(state, action: PayloadAction<INode>) {
            const newNode = action.payload
            state.nodes = [...state.nodes.filter((it) => it.id != newNode.id)].concat(newNode)
        },
        addEdge(state, action: PayloadAction<IEdge>) {
            state.edges = state.edges.concat(action.payload)

        }
    }
})

export const graphReducer = graphSlice.reducer