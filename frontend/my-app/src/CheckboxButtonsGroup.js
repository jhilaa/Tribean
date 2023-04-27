import React, {useState, useEffect} from "react";
import "./CheckboxButtonsGroup.scss";

const CheckBoxButtonsGroup = props => {

    const [checkBox, setCheckBox] = useState(props.checkboxObj);
    const [selectedVal, setSelectedVal] = useState(null);

    useEffect(() => {
    }, []);

    const handleChange = (e, val, index) => {
        let data = checkBox;
        data[index].selected = !data[index].selected;
        setCheckBox([...data]);
        setSelectedVal(checkBox.filter(tag => tag.selected));
    };

    const {align, design, width, length} = props.commonStyle;
    return (
            <div
                className={`custom-form-control ${design} ${align} ${
                    length >= 2 ? "first-last" : "no-list"
                }`}
                style={{width: width}}
            >
                {checkBox &&
                    checkBox.map((val, i) => {
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
