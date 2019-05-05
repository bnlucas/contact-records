import React from 'react';
import JsonTable from 'ts-react-json-table';

import FileUpload from './FileUpload';

class Main extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            duplicates: [],
            unique: [],
        };

        this.setContacts = this.setContacts.bind(this);
    }

    setContacts(contacts) {
        this.setState({ ...contacts });
    }

    render() {
        const { duplicates, unique } = this.state;

        return (
            <React.Fragment>
                <FileUpload setContacts={this.setContacts} />
                <div style={{ margin: "10px 0", }}>
                    <JsonTable rows={duplicates} columns={["id", "first_name", "last_name", "company", "email", "address1", "address2", "zip", "city", "state_long", "state", "phone"]} />
                </div>
                <div style={{ margin: "10px 0", }}>
                    <JsonTable rows={unique} columns={["id", "first_name", "last_name", "company", "email", "address1", "address2", "zip", "city", "state_long", "state", "phone"]} />
                </div>
            </React.Fragment>
        );
    }
}

export default Main;
