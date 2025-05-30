const jwt = require('jsonwebtoken');
const JWT_SECRET = '123456789'; // Same secret

const verifyToken = (req, res, next) => {
  const token = req.headers.authorization.split(' ')[1];
  console.log(JSON.stringify(token));
; 
  if (!token) return res.status(401).json({ message: 'Access denied. No token provided.' });

  try {
    const decoded = jwt.verify(token, JWT_SECRET);
    req.user = decoded;
    next();
  } catch (err) {
    res.status(401).json({ message: 'Invalid token' });
  }
};

module.exports = verifyToken;
