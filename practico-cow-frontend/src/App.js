import React, { Component } from 'react';
//import './App.css';

class App extends Component {
  constructor() {
  	super();
  	this.state = {initPoint: "", preferenceId: ""};
  }

  componentWillMount() {
  	fetch('http://localhost:8080/practico/preferencia')
        .then((result) => result.json())
  		.then((initPointResp) => {this.setState({initPoint : initPointResp.initPoint,
                                                 preferenceId : initPointResp.id});
  	                              this.buildPunto5();})
  }

  buildPunto5() {
      var script = document.createElement("script");
      script.setAttribute("src", "https://www.mercadopago.com.ar/integrations/v1/web-tokenize-checkout.js");
      script.setAttribute("data-preference-id", this.state.preferenceId);

      var form = document.createElement("form");
      form.setAttribute("action", "http://localhost:8080/practico/procesar-pago");
      form.setAttribute("method", "POST");
      form.appendChild(script);

      document.getElementById("divPunto5").appendChild(form);
  }

  render() {
    return (
        <div>
            <form>
                <h3>Punto 1</h3>
                <h4>{this.state.initPoint}</h4>
                <a href={this.state.initPoint} name="MP-Checkout" className="green-L-Rn" mp-mode="redirect">Pagar</a>
            </form>

    	<form>
          <h3>Punto 2</h3>
          <a href={this.state.initPoint} name="MP-Checkout" className="green-L-Rn" mp-mode="modal">Pagar</a>
          <a href={this.state.initPoint} name="MP-Checkout" className="blue-L-Rn" mp-mode="popup">Pagar</a>
          <a href={this.state.initPoint} name="MP-Checkout" className="orange-L-Rn" mp-mode="blank">Pagar</a>
          <a href={this.state.initPoint} name="MP-Checkout" className="grey-L-Rn" mp-mode="redirect">Pagar</a>
        </form>

          <form action="http://localhost:8080/practico/pago" method="post" id="pay" name="pay" >
          <h3>Punto 3</h3>
          <fieldset>
          <ul>
          <li>
          <label for="email">Email</label>
          <input id="email" name="email" value="test_user_19653727@testuser.com" type="email" placeholder="your email"/>
          </li>
          <li>
          <label for="cardNumber">Credit card number:</label>
      <input type="text" id="cardNumber" data-checkout="cardNumber" placeholder="4509 9535 6623 3704" />
          </li>
          <li>
          <label for="securityCode">Security code:</label>
      <input type="text" id="securityCode" data-checkout="securityCode" placeholder="123" />
          </li>
          <li>
          <label for="cardExpirationMonth">Expiration month:</label>
      <input type="text" id="cardExpirationMonth" data-checkout="cardExpirationMonth" placeholder="12" />
          </li>
          <li>
          <label for="cardExpirationYear">Expiration year:</label>
      <input type="text" id="cardExpirationYear" data-checkout="cardExpirationYear" placeholder="2015" />
          </li>
          <li>
          <label for="cardholderName">Card holder name:</label>
      <input type="text" id="cardholderName" data-checkout="cardholderName" placeholder="APRO" />
          </li>
          <li>
          <label for="docType">Document type:</label>
      <select id="docType" data-checkout="docType"></select>
          </li>
          <li>
          <label for="docNumber">Document number:</label>
      <input type="text" id="docNumber" data-checkout="docNumber" placeholder="12345678" />
          </li>
          <li>
          <label for="installments">Installments:</label>
      <select id="installments" name="installments" data-checkout="installments"></select>
          </li>
          </ul>
          <input type="hidden" name="paymentMethodId" id="paymentMethodId" />
          <input type="submit" value="Pay!" />
          </fieldset>
          </form>
          </div>
    );
  }
}

export default App;
