import React from 'react';
import JsonTable from 'ts-react-json-table';

import FileUpload from './FileUpload';
import ContactsTable from './ContactsTable';

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
                <ContactsTable title="Potential Duplicates" data={duplicates} />
                <ContactsTable title="Unique:" data={unique} />
            </React.Fragment>
        );
    }
}

export default Main;
