import {useParams} from "react-router-dom";

export function AddResource () {
    let {resourceId} = useParams();
    if (resourceId) {
        return "updateResource";
    }
    return "addResource";
}