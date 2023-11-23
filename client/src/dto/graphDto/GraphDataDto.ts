import {NodeDataDto} from "./NodeDataDto";
import {EdgeDataDto} from "./EdgeDataDto";

export interface GraphDataDto {
    nodes: NodeDataDto[],
    edges: EdgeDataDto[]
}