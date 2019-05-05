import React from 'react';

class FileUpload extends React.Component {
    constructor(props) {
        super(props);

        this.uploadFile = this.uploadFile.bind(this);
    }

    uploadFile(e) {
        e.preventDefault();

        const { setContacts } = this.props;

        const data = new FormData();
        data.append('file', this.uploadInput.files[0]);

        fetch('http://localhost:8080/api/file', {
            method: 'POST',
            body: data,
        })
            .then(response => response.json())
            .then(data => setContacts(data));
    }

    render() {
        return (
            <form onSubmit={this.uploadFile}>
                <div>
                    <input ref={(ref) => { this.uploadInput = ref; }} type="file" />
                </div>
                <br />
                <div>
                    <button>Upload</button>
                </div>
            </form>
        );
    }
}

export default FileUpload;
