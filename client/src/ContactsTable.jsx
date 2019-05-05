import React from 'react';
import JsonTable from 'ts-react-json-table';

export default (props) => {
    return (
        <div style={{ margin: "40px 20px", }}>
            <h3>{props.title}</h3>
            <JsonTable
                rows={props.data}
                columns={[
                    "id", "first_name", "last_name",
                    "company", "email", "address1",
                    "address2", "zip", "city",
                    "state_long", "state", "phone",
                ]}
            />
        </div>
    );
};
