class ShowDesignation extends React.Component {
  constructor(props) {
    super(props);
    this.state={list:[],designationSet:{name:"",
    code:"",description:"", chartOfAccounts:"",active:""}}

  }


  componentDidMount()
  {
    try {
        var _designation = commonApiPost("hr-masters","designations","_search",{tenantId,pageSize:500}).responseJSON["Designation"] || [];
    } catch(e) {
        var _designation = [];
    }
    this.setState({
      list:_designation
    });

  }

  componentDidUpdate(prevProps, prevState)
  {
      if (prevState.list.length!=this.state.list.length) {

          $('#designationTable').DataTable({
            dom: 'Bfrtip',
            buttons: [
                     'copy', 'csv', 'excel', 'pdf', 'print'
             ],
             ordering: false
          });
      }
  }


  close(){
      // widow.close();
      open(location, '_self').close();
  }

  render() {
    let {list}=this.state;
    let {name,code,description,active}=this.state.designationSet;
    var mode = getUrlVars()["type"];

    const renderAction=function(type,id){
      if (type==="Update") {

              return (
                      <a href={`app/hr/master/designation.html?id=${id}&type=${type}`} className="btn btn-default btn-action"><span className="glyphicon glyphicon-pencil"></span></a>
              );

    }else {
            return (
                    <a href={`app/hr/master/designation.html?id=${id}&type=${type}`} className="btn btn-default btn-action"><span className="glyphicon glyphicon-modal-window"></span></a>
            );
        }
}


    const renderBody=function()
    {
      return list.map((item,index)=>
      {
            return (<tr key={index}>
                    <td>{index+1}</td>
                    <td data-label="name">{item.name}</td>
                    <td data-label="code">{item.code}</td>
                    <td data-label="description">{item.description}</td>
                    <td data-label="active">{item.active?"true":"false"}</td>
                    <td data-label="action">
                    {renderAction(getUrlVars()["type"],item.id)}
                    </td>
                </tr>
            );

      })
    }

      return (<div>
        <h3>{mode} Designation </h3>
        <table id="designationTable" className="table table-bordered">
            <thead>
                <tr>
                    <th>Sl No.</th>
                    <th>Name</th>
                    <th>Code</th>
                    <th>Description</th>
                    <th>Active</th>
                    <th>Action</th>

                </tr>
            </thead>

            <tbody id="employeeSearchResultTableBody">
                {
                    renderBody()
                }
            </tbody>

        </table>
        <div className="text-center">
            <button type="button" className="btn btn-close"onClick={(e)=>{this.close()}}>Close</button>
        </div>
      </div>
    );
  }
}


ReactDOM.render(
  <ShowDesignation />,
  document.getElementById('root')
);
