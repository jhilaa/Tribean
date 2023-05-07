import React, {useState, useEffect} from "react";
import "./CheckboxButtonsGroup.scss";
import axios from "axios";
import Spinner from 'react-bootstrap/Spinner';


function CheckBoxButtonsGroup({setSelectedTags}) {

    const [checkBoxList, setCheckBoxList] = useState([]);
    const [showSpinner, setShowSpinner] = useState( false);

    useEffect(() => {
        axios.get("/tags/all").then(response => {
            setCheckBoxList(response.data.map(e => ({...e, selected: false})));
        }).then(()=> {setShowSpinner(!showSpinner)});
    }, []);

    const handleChange = (e, val, index) => {
        let data = checkBoxList;
        data[index].selected = !data[index].selected;
        setCheckBoxList([...data]);
        setSelectedTags(checkBoxList.filter(tag => tag.selected));
        //--------
    };

    const {align, design, width, length, icon} = {
        design: "button-style",
        align: "horizontal",
        width: 350,
        length: {checkBoxList}.length,
        icon: true
    };
    return (
        <div
            className={`custom-form-control ${design} ${align} ${
                length >= 2 ? "first-last" : "no-list"
            }`}
            style={{width: width}}
        >
            <div>
                <Spinner animation="border" role="status" hidden={showSpinner}>
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            </div>
            {checkBoxList &&
                checkBoxList.map((val, i) => {
                    return (
                        <div key={val.id} className="checkbox-list">
                            <input
                                type="checkbox"
                                name={val.name}
                                id={val.id}
                                checked={val.selected}
                                onChange={event => handleChange(event, val.name, i)}
                            />
                            <label htmlFor={val.id}>{val.name}</label>
                        </div>
                    );
                })}
            <br/>
        </div>
    );
};

export default CheckBoxButtonsGroup;
