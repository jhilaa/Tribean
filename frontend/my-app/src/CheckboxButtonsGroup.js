import React, {useState, useEffect} from "react";
import "./CheckboxButtonsGroup.scss";
import axios from "axios";

function  CheckBoxButtonsGroup ({setSelectedTags}) {

    const [checkBoxList, setCheckBoxList] = useState();
    const [selectedVal, setSelectedVal] = useState(null);

    useEffect(() => {
        axios.get("/tags/all").then(response => {
            setCheckBoxList(response.data);
    })}, []);

    const handleChange = (e, val, index) => {
        let data = checkBoxList;
        data[index].selected = !data[index].selected;
        setCheckBoxList([...data]);
        setSelectedVal(checkBoxList.filter(tag => tag.selected));
        //--------
    };

    const {align, design, width, length, icon} ={
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
                {checkBoxList &&
                    checkBoxList.map((val, i) => {
                        return (
                            <div key={i} className="checkbox-list">
                                <input
                                    type="checkbox"
                                    name={val.name}
                                    id={i}
                                    checked={val.selected}
                                    onChange={event => handleChange(event, val.name, i)}
                                />
                                <label htmlFor={i}>{val.name}</label>
                            </div>
                        );
                    })}
                <br/>
            </div>
    );
};

export default CheckBoxButtonsGroup;
