import {EdgeQueryDto} from "./EdgeQueryDto";
import {NodeQueryDto} from "./NodeQueryDto";


export interface GraphQueryDto {
    nodes: NodeQueryDto[],
    edges: EdgeQueryDto[]
}