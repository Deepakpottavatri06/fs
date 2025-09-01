const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/progress_reports', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// Schema & Model


const reportSchema = new mongoose.Schema({
  name: String,
  subject: String,
  score: Number,
  remarks: String,
  performance: String,
  date: { type: Date, default: Date.now }
});
const Report = mongoose.model('Report', reportSchema);

// API Route
const dummyRemarks = [
  "Shows consistent improvement.",
  "Needs to participate more actively.",
  "Excellent grasp of concepts.",
  "Should focus on assignments.",
  "Great teamwork skills.",
  "Attendance can be improved.",
  "Demonstrates leadership qualities."
];

function getRandomRemark() {
  const idx = Math.floor(Math.random() * dummyRemarks.length);
  return dummyRemarks[idx];
}

// Generate a detailed performance description (200+ words)
// Add this helper function at the top of your file
function generatePerformance(name, subject, score, remarks) {
  if (score >= 75) {
    return `${name} has demonstrated great performance in ${subject} with a score of ${score}. ${remarks} This achievement reflects a strong understanding of the subject matter, consistent effort, and a commendable attitude towards learning. ${name} participates actively, submits assignments on time, and often exceeds expectations in both individual and group tasks. Such dedication not only benefits ${name}'s academic progress but also sets a positive example for peers. Maintaining this level of commitment will ensure continued success in future assessments and projects. The school encourages ${name} to keep up the excellent work and explore further opportunities for growth in ${subject}.`;
  } else if (score < 75 && score > 50) {
    return `${name} has achieved an average performance in ${subject}, scoring ${score}. ${remarks} While ${name} shows a reasonable grasp of the core concepts, there is potential for improvement through more focused study and active participation in class activities. Timely submission of assignments and better engagement during lessons can help ${name} strengthen understanding and achieve higher results. Teachers recommend that ${name} seek guidance whenever difficulties arise and make use of available resources. With continued effort and a positive approach, ${name} can improve performance in ${subject} and reach new academic milestones.`;
  } else {
    return `${name} has displayed below average performance in ${subject}, scoring only ${score}. ${remarks} This result indicates that ${name} faces significant challenges in understanding the material and completing assignments. It is important for ${name} to seek support from teachers, attend extra help sessions, and dedicate more time to studying the subject. Consistent effort, asking questions when in doubt, and practicing regularly can greatly enhance ${name}'s grasp of key concepts. The school believes in ${name}'s potential and encourages perseverance and hard work to overcome current difficulties and achieve better results in future assessments.`;
  }
}

// Update your POST handler as follows:
app.post('/api/report', async (req, res) => {
  let data = req.body;

  // Fill dummy remarks if not provided
  if (!data.remarks || data.remarks.trim() === "") {
    data.remarks = getRandomRemark();
  }

  // Generate performance narrative
  data.performance = generatePerformance(data.name, data.subject, data.score, data.remarks);

  const report = new Report(data);
  await report.save();
  res.json(report);
});

app.get('/api/report/:id', async (req, res) => {
  const report = await Report.findOne({ id: parseInt(req.params.id) });
  if (!report) {
    return res.status(404).json({ error: "Report not found" });
  }
  res.json(report);
});
const PORT = 5000;
app.listen(PORT, () => console.log(`Server started on ${PORT}`));