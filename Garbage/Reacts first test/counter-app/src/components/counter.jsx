import React, { Component } from "react";

class Counter extends Component {
	state = {
		value: this.props.value
	};

	render() {
		return (
			<div>
				<span className={this.getBadgeClasses()}>{this.formateCount()}</span>
				<button
					onClick={() => this.handleIncrement({ id: 1 })}
					className="btn btn-secondary btn-sm"
				>
					Increment
				</button>
			</div>
		);
	}

	handleIncrement = product => {
		this.setState({ count: this.state.value + 1 });
	};

	getBadgeClasses() {
		let classes = "badge m-2 badge-";
		classes += this.state.value === 0 ? "warning" : "primary";
		return classes;
	}

	formateCount() {
		const { value: count } = this.state;
		return count === 0 ? "Zero" : count;
	}
}

export default Counter;
