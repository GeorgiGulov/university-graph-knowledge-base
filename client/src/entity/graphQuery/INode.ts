import {IProperty} from "./IProperty";


export interface INode {
    id: number
    label: string,
    property: IProperty[]
}