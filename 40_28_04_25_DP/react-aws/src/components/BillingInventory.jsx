import { useState } from 'react';
import axios from 'axios';

export default function BillingInventoryForm() {
  const [formData, setFormData] = useState({
    name: '',
    amount: '',
    quanity: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('https://api.your-region.amazonaws.com/form-submit', formData,
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      alert(response.data.message);
    } catch (error) {
      console.error(error);

      alert('Submission failed.');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="name" placeholder="Name" onChange={handleChange} />
      <input name="amount" placeholder="Amount" onChange={handleChange} />
      <input name="quanity" placeholder="Quanity" onChange={handleChange} />
      <button type="submit">Submit</button>
    </form>
  );
}
