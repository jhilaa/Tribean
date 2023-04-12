import React from 'react';

export class Resource extends React.Component {

    constructor() {
        super();
    }

    // props en paramètre implicite
    render() {
        return <div>
            {this.props.title}
            <div>
            <button>Modifier</button>
            <button>Supprimer</button>
            </div>
        </div>
    }
}

